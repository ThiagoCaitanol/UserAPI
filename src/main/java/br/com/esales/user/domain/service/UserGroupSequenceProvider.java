package br.com.esales.user.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import br.com.esales.user.domain.model.User;

public class UserGroupSequenceProvider implements DefaultGroupSequenceProvider<User>{

	@Override
	public List<Class<?>> getValidationGroups(User user) {
		List<Class<?>> groups = new ArrayList<>();
		groups.add(User.class);
		
		if(isPersonSelected(user)) {
			groups.add(user.getPersonType().getGroup());
		}
		
		return groups;
	}
	
	protected boolean isPersonSelected(User user) {
		return user != null && user.getPersonType() != null;
 	}
}
