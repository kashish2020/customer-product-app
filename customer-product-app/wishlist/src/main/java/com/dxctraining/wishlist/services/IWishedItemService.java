package com.dxctraining.wishlist.services;

import java.util.List;

import com.dxctraining.wishlist.entities.WishedItem;

public interface IWishedItemService {

	WishedItem add(WishedItem item);

	void remove(String id);

	WishedItem findById(String id);

	List<WishedItem> findAllById(Integer customerId);
    
    List<WishedItem> allWishedItems();

}
