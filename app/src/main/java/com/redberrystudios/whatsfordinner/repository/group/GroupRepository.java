package com.redberrystudios.whatsfordinner.repository.group;

import com.redberrystudios.whatsfordinner.api.WFDApiService;

public class GroupRepository {

    private GroupDao groupDao;

    private WFDApiService wfdApiService;

    public GroupRepository(GroupDao groupDao, WFDApiService wfdApiService) {
        this.groupDao = groupDao;
        this.wfdApiService = wfdApiService;
    }
}
