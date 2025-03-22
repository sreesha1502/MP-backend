package com.vegobject.springboot_mongodb.service;

import com.vegobject.springboot_mongodb.collection.CapturedDates;
import com.vegobject.springboot_mongodb.collection.Poles;

public interface PolesService {

    Poles[] getPoles();

    CapturedDates getCapturedDateStrings();
}
