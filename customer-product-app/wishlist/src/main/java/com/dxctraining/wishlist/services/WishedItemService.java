package com.dxctraining.wishlist.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.wishlist.dao.IWishedItemDao;
import com.dxctraining.wishlist.entities.WishedItem;
import com.dxctraining.wishlist.exceptions.InvalidArgumentException;
import com.dxctraining.wishlist.exceptions.WishedItemException;

@Service
@Transactional
public class WishedItemService implements IWishedItemService {

	private String generateId() {
		Random randNum = new Random();
		int generate = randNum.nextInt(1000);
		return "Wishlistitem-" + generate + "";
	}

	@Autowired
	private IWishedItemDao dao;

	@Override
	public WishedItem add(WishedItem item) {
		String id = generateId();
		item.setId(id);
		item = dao.save(item);
		return item;
	}

	@Override
	public void remove(String id) {
		dao.deleteById(id);

	}

	@Override
	public WishedItem findById(String id) {
		Optional<WishedItem> optional = dao.findById(id);
		boolean exist = optional.isPresent();
		if (!exist) {
			throw new WishedItemException("Item not found " + id);
		}
		WishedItem item = optional.get();
		return item;
	}

	private void validate(Object obj) {
		if (obj == null) {
			throw new InvalidArgumentException("WishedItem cant be null");
		}
	}

	@Override
	public List<WishedItem> findAllById(Integer customerId) {
		validate(customerId);
		List<WishedItem> list = dao.findAllById(customerId);
		return list;
	}

	@Override
	public List<WishedItem> allWishedItems() {
		List<WishedItem> list = dao.findAll();
		return list;
	}
	
}
