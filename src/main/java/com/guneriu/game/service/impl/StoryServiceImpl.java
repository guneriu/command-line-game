package com.guneriu.game.service.impl;

import com.guneriu.game.model.Story;
import com.guneriu.game.service.StoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for accessing {@link Story} objects
 *
 * Created by ugur on 26.06.2016.
 */
public class StoryServiceImpl implements StoryService {

    private static List<Story> storyList = new ArrayList<>();

    @Override
    public void load(List<Story> stories) {
        storyList.addAll(stories);

    }

    @Override
    public Story get(String id) {
        return storyList.stream().filter(story -> story.getId().equals(id)).findFirst().get();
    }

    @Override
    public List<Story> getAll() {
        return storyList;
    }

    @Override
    public boolean isAllCompleted() {
        return this.getAll().stream().allMatch(Story::isCompleted);
    }


}