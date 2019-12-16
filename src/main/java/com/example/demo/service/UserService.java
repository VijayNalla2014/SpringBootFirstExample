package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
public class UserService {
	
	Map<String,User> map = null;
	
	public Boolean saveUserDetails(User user) {
		if(map == null) {
			map = new HashMap<String,User>();
		}
		map.put(user.getName(), user);
		return Boolean.TRUE;
	}
	
	public User getUserDetails(String name){
		if(map==null)
			return null;
		else {
			return map.get(name);
		}
			
	}

	public User deleteUserDetails(String name) {
		if(map==null)
			return null;
		else {
			return map.remove(name);
		}				
	}
	
	public List<User> getAllUsers(){
		List<User> list = null;
		if(map!=null) {
			list = new ArrayList<User>();
			Iterator<Entry<String,User>> it = map.entrySet().iterator();
			while(it.hasNext()) {
				list.add(it.next().getValue());
			}
		}
		return list;
	}
}
