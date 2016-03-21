package com.apps.base.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.apps.blog.back.bean.User;

public class OneUserUtils {
	
	private final static Logger log = Logger.getLogger(OneUserUtils.class);
	
	private static List<User> loginUsers;
	
	public static boolean hasOldUser(User checkUser){
		boolean hasUser = false;
		if(null!=loginUsers && loginUsers.size()>0){
			for (int i = 0; i < loginUsers.size(); i++) {
				if(checkUser.getId() == loginUsers.get(i).getId()){
					hasUser = true;
					break;		
				}
			}
		}
		if(!hasUser) addUser(checkUser);
		return hasUser;
	}
	
	public static void addUser(User loginUser){
		if(null == loginUsers){
			loginUsers = new ArrayList<User>();
		}
		loginUsers.add(loginUser);
		
		printList("session list addUser", loginUsers);
	}
	
	public static void removeLogUser(User logoutUser){
		if(null == loginUsers){
			loginUsers = new ArrayList<User>();
		}
		if(null != loginUsers && loginUsers.size()>0){
			for (int i = 0; i < loginUsers.size(); i++) {
				if(logoutUser.getId() == loginUsers.get(i).getId()){
					loginUsers.remove(logoutUser);
					log.info("session list removeUser:" + logoutUser.getId() + " - " + logoutUser.getUsername());
					break;
				}
			}
		}
		printList("hasUser", loginUsers);
	}
	
	
	public static void printList(String method, List<User> lists){
		log.info(method + " size:" + lists.size() + "---start---");
		if(null!=lists && lists.size()>0){
			for (int i = 0; i < lists.size(); i++) {
				log.info(lists.get(i).getId()+"\t"+lists.get(i).getUsername());
			}
		}
		log.info("---end---");
	}
}
