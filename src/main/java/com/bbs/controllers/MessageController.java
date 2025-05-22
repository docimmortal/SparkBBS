package com.bbs.controllers;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.entites.Message;
import com.bbs.entites.MessageForum;
import com.bbs.entites.Reaction;
import com.bbs.entites.YoutubeVideo;
import com.bbs.enums.ReactionType;
import com.bbs.entites.BBSUserDetails;
import com.bbs.services.BBSUserDetailsService;
import com.bbs.services.LastReadMessageService;
import com.bbs.services.MessageForumService;
import com.bbs.services.MessageService;
import com.bbs.utilities.MenuUtilities;
import com.bbs.utilities.MessageUtilities;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MessageController {

	@Autowired
	private MessageService mService;
	
	@Autowired
	private MessageForumService mfService;
	
	@Autowired
	private BBSUserDetailsService budService;
	
	@Autowired
	private LastReadMessageService lrmService;
	
	@PostMapping("/readMessage")
	public String readMessage(@RequestParam(required=true) String userDetailsId,
			@RequestParam(required = false, defaultValue="0") String messageId, 
			@RequestParam(required = false, defaultValue="") String messageForumId,
			@RequestParam(required = false, defaultValue="") String action,
			@RequestParam(required = false, defaultValue="") String nextForumId,
			@RequestParam(required = false, defaultValue="") String nextMessageId,
			@RequestParam(required = false, defaultValue="") String prevForumId,
			@RequestParam(required = false, defaultValue="") String prevMessageId,
			Model model, HttpServletRequest request, HttpSession session) {
		
		// Cleansing data, removing non-numerics from String
		messageId=messageId.replaceAll("[^0-9]", "");
		messageForumId=messageForumId.replaceAll("[^0-9]", "");
		userDetailsId=userDetailsId.replaceAll("[^0-9]", "");
		nextForumId=nextForumId.replaceAll("[^0-9]", "");
		nextMessageId=nextMessageId.replaceAll("[^0-9]", "");
		prevForumId=prevForumId.replaceAll("[^0-9]", "");
		prevMessageId=prevMessageId.replaceAll("[^0-9]", "");
		
		System.out.println("Got userDetailsId: "+userDetailsId+", messageId: "+messageId+
				", messageForumId: "+messageForumId);
		
		model.addAttribute("userDetailsId",userDetailsId);
		model.addAttribute("previousEmojiValue",null);
		model.addAttribute("currentEmojiCount",0);
		BigInteger currentMessageId=null;
		BigInteger forumId=null;
		Message message=null;
		
		// First time hitting the readMessage logic, look for an unread message
		if (messageForumId.isBlank()) {
			System.out.println("Looking for next unread message for userId:"+userDetailsId);
			BigInteger detailsId = new BigInteger(userDetailsId);
			BigInteger[] results = lrmService.getNextForumWithUnreadMessages(detailsId, BigInteger.ZERO);
			if (results != null && results.length==2 && !results[0].equals(BigInteger.ZERO)) {
				forumId=results[0];
				currentMessageId=results[1];
			}
		} else {
			currentMessageId=new BigInteger(messageId);
			forumId = new BigInteger(messageForumId);
		}
		System.out.println("Message ID: "+currentMessageId+", Message Forum ID: "+forumId);
		
		// Logic for going to prev/next forum
		if (action.equalsIgnoreCase("NextForum")) {
			forumId=new BigInteger(nextForumId);
			currentMessageId=new BigInteger(nextMessageId);
			action="next";
		} else if (action.equalsIgnoreCase("PrevForum") && !forumId.equals(BigInteger.ONE)) {
			// This will change to SQL to search backwards
			forumId=new BigInteger(prevForumId);
			Optional<Message> msg=mService.getLastMessageInMessageForum(forumId);
			if (msg.isPresent()) {
				// for previous logic, it looks for a number lower than currentMessageId.
				// So, to get last msg, we need last messageId + 1
				currentMessageId = msg.get().getId().add(BigInteger.ONE);
			}
			action="prev";
		}
		
		// Gets next or previous message 
		if (forumId != null) {
			message = getMesage(action, forumId, currentMessageId);
			if (message == null) {
				System.out.println("ERROR: Message is null!");
			} else {
				BigInteger detailsId = new BigInteger(userDetailsId);
				setNavigation(detailsId,message.getId(), message.getMessageForum().getId(), model);			
			}
			model.addAttribute("message",message);
		}

		model.addAttribute("menus",MenuUtilities.getMenus());
		return "messages/readMessage";
	}
	
	@PostMapping("/saveMessage")
	public String saveMessage(@RequestParam(required=true) String userDetailsId,
			@RequestParam(required = true) String messageForumId,
			@RequestParam(required = true) String newTitle,
			@RequestParam String newMessageText,
			@RequestParam String newVideoEndpoint,
			@RequestParam(required = true) String lastReadMessageId,
			Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("userDetailsId: "+userDetailsId);
		System.out.println("messageForumId: "+messageForumId);
		System.out.println("lastReadMessageId: "+lastReadMessageId);
		System.out.println("newTitle: "+newTitle);
		System.out.println("newMessageText: "+newMessageText);
		System.out.println("newMessageText: "+newVideoEndpoint);
		
		// Cleansing data
		messageForumId=messageForumId.replaceAll("[^0-9]", "");
		userDetailsId=userDetailsId.replaceAll("[^0-9]", "");
		lastReadMessageId=lastReadMessageId.replaceAll("[^0-9]", "");
		newTitle = MessageUtilities.cleanMessage(newTitle);
		newMessageText = MessageUtilities.cleanMessage(newMessageText);
		if (newVideoEndpoint.length()!=11) {
			newVideoEndpoint="";
		}
		
		String url="error";
		
		if (!messageForumId.isBlank() && !userDetailsId.isBlank() && !newMessageText.isBlank() &&!newTitle.isBlank()) {			
			Optional<MessageForum> forum = mfService.findById(new BigInteger(messageForumId));
			Optional<BBSUserDetails> user = budService.findById(new BigInteger(userDetailsId));
		
			if (forum.isPresent() && user.isPresent()) {
					Message message = new Message();
					message.setMessage(newMessageText);
					message.setTimestamp(LocalDateTime.now());
					message.setTitle(newTitle);
					message.setMessageForum(forum.get());
					message.setBbsUserDetails(user.get());
					System.out.println("Message Save "+message.getId());
					
					YoutubeVideo vid = null;
					if (newVideoEndpoint.length()==11) {
						vid = new YoutubeVideo();
						vid.setEndpoint(newVideoEndpoint);
						message.setYoutubeVideo(vid);
					}

					message=mService.save(message);

					model.addAttribute("userDetailsId",userDetailsId);
					System.out.println("lastReadMessageId: "+lastReadMessageId);

					Optional<Message> origMsg = mService.findById(new BigInteger(lastReadMessageId));
					model.addAttribute("message",origMsg.get());
					setNavigation(userDetailsId,lastReadMessageId, messageForumId, model);	
					model.addAttribute("menus",MenuUtilities.getMenus());
					url="messages/readMessage";
			} else {
				if (forum.isEmpty()) System.out.println("Forum for "+messageForumId+" not found.");
				if (user.isEmpty()) System.out.println("UserDetails for "+userDetailsId+" not found.");
			}
		} else {
			if (messageForumId.isBlank()) System.out.println("MessageForumId is blank or invalid.");
			if (userDetailsId.isBlank()) System.out.println("UserDetailsId is blank or invalid.");
			if (newMessageText.isBlank()) System.out.println("No message text found.");
			if (newTitle.isBlank()) System.out.println("No title found.");
		}

		return url;
	}
	
	@PostMapping("/saveReaction")
	public String saveReaction(@RequestParam(required=true) String userDetailsId,
			@RequestParam(required = true) String lastReadMessageId, 
			@RequestParam(required = false, defaultValue="") String messageForumId,
			@RequestParam(required = true) String reaction,
			Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("userDetailsId: "+userDetailsId);
		System.out.println("messageForumId: "+messageForumId);
		System.out.println("lastReadMessageId: "+lastReadMessageId);
		System.out.println("Add reaction: "+reaction);
		
		// Cleansing data
		messageForumId=messageForumId.replaceAll("[^0-9]", "");
		userDetailsId=userDetailsId.replaceAll("[^0-9]", "");
		lastReadMessageId=lastReadMessageId.replaceAll("[^0-9]", "");
			
		// Fetch message
		String url="messages/readMessage";
		Optional<Message> origMsg = mService.findById(new BigInteger(lastReadMessageId));
		if (origMsg.isEmpty()) System.out.println("Message not found?!");
		Message msg=origMsg.get();
		
		// Logic to add another reaction to this message.
		Optional<BBSUserDetails> userInfo = budService.findById(new BigInteger(userDetailsId));
		Reaction newReaction = null;
		if (userInfo.isPresent()) {
			try {
				System.out.println("Found the user");
				ReactionType rt=ReactionType.valueOf(reaction.toUpperCase());
				System.out.println("Found the reaction type "+rt);
				newReaction = new Reaction();
				newReaction.setReactionType(rt);
				newReaction.setMessage(msg);
				newReaction.setBbsUserDetails(userInfo.get());
				msg.getReactions().add(newReaction);
				msg=mService.save(msg);
			} catch (IllegalArgumentException e) {
				// Invalid reaction. Add nothing.
			}
		} // else, invalid user. Add nothing.
		
		model.addAttribute("message",msg);
		model.addAttribute("reaction","react "+reaction);
		setNavigation(userDetailsId,lastReadMessageId, messageForumId, model);	
		model.addAttribute("menus",MenuUtilities.getMenus());
		return url;
	}
	
	@PostMapping("/removeReaction")
	public String removeReaction(@RequestParam(required=true) String userDetailsId,
			@RequestParam(required = true) String lastReadMessageId, 
			@RequestParam(required = false, defaultValue="") String messageForumId,
			@RequestParam(required = true) String reaction,
			Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("userDetailsId: "+userDetailsId);
		System.out.println("messageForumId: "+messageForumId);
		System.out.println("lastReadMessageId: "+lastReadMessageId);
		System.out.println("Remove reaction: "+reaction);
		
		// Cleansing data
		messageForumId=messageForumId.replaceAll("[^0-9]", "");
		userDetailsId=userDetailsId.replaceAll("[^0-9]", "");
		lastReadMessageId=lastReadMessageId.replaceAll("[^0-9]", "");
		
		// TODO, Logic to add another reaction to this message.
		
		String url="messages/readMessage";
		Optional<Message> origMsg = mService.findById(new BigInteger(lastReadMessageId));
		model.addAttribute("message",origMsg.get());
		setNavigation(userDetailsId,lastReadMessageId, messageForumId, model);	
		model.addAttribute("menus",MenuUtilities.getMenus());
		return url;
		
	}
	
	private void setNavigation(String detailsId, String currentMessageId, 
			String currentForumId, Model model) {
		BigInteger dId = new BigInteger(detailsId);
		BigInteger mId = new BigInteger(currentMessageId);
		BigInteger fId = new BigInteger(currentForumId);
		setNavigation(dId, mId, fId, model);
	}
	
	private void setNavigation(BigInteger detailsId, BigInteger currentMessageId, 
			BigInteger currentForumId, Model model) {
		
		Optional<Message> mOptional = mService.findPrevInMessageForum(currentMessageId, currentForumId);
		if (mOptional.isPresent()) {
			model.addAttribute("hasPrev", mOptional.get().getId());
		} else {
			if (currentForumId.compareTo(BigInteger.ONE)==1) {
				Optional<Message> mLast = null;
				BigInteger priorForumId = currentForumId;
				do {
					priorForumId = priorForumId.subtract(BigInteger.ONE);
					mLast = mService.getLastMessageInMessageForum(priorForumId);
				} while (mLast.isEmpty() && priorForumId.compareTo(BigInteger.ONE)==1);
				if (mLast.isPresent()) {
					model.addAttribute("prevMessageId",mLast.get().getId());
					model.addAttribute("prevForumId", priorForumId);
				}
			}
		}
		
		// Is there another message in the current forum?
		mOptional = mService.findNextInMessageForum(currentMessageId,currentForumId);
		if (mOptional.isPresent()) {
			model.addAttribute("hasNext", mOptional.get().getId());
		} else {
			// Is there a next forum with unread messages?
			BigInteger[] results = lrmService.getNextForumWithUnreadMessages(detailsId, currentForumId);
			if (results != null && results.length==2 && !results[0].equals(BigInteger.ZERO)) {
				BigInteger nextForumIdWithMsgs=results[0];
				BigInteger lastReadId=results[1];
				model.addAttribute("nextForumId", nextForumIdWithMsgs);
				model.addAttribute("nextMessageId", lastReadId);
			} else {
				System.out.println("No more messages");
			}
		}
	}
	
	private Message getMesage(String action, BigInteger forumId, BigInteger currentId) {
		System.out.println("getMessage: Action is ["+action+"]");
		Optional<Message> moptional=null;
		if (action.equalsIgnoreCase("next") || action.equals("")) {
			//System.out.println("getMessage: Getting next message from forum "+forumId+". Current message is "+currentId+".");
			moptional = mService.findNextInMessageForum(currentId,forumId);
		} else if (action.equalsIgnoreCase("prev")) {
			//System.out.println("getMessage: Getting prev message from forum "+forumId+". Current message is "+currentId+".");
			moptional = mService.findPrevInMessageForum(currentId, forumId);
		}
		Message message  = null;
		if (moptional.isPresent()) {
			message = moptional.get();
			//System.out.println("getMessage: Found a real message! id:"+message.getId());

		}
		return message;
	}	
}
