package com.guneriu.game.io;

import com.guneriu.game.model.Hero;
import com.guneriu.game.model.Story;
import com.guneriu.game.provider.WeaponProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse List of String and creates {@code Order} and {@code Customer} lists
 */
public class StoryParser implements Parser<Story> {
    private List<Story> storyList = new ArrayList<>();
    private String delimeter;

    /**
     * uses given delimiter to split line
     *
     * @param delimeter
     */
    public StoryParser(String delimeter) {
        this.delimeter = delimeter;
    }

    /**
     * uses {@code List<String>} and parses {@code Customer} and {@code Order} objects
     */
    @Override
    public void parseContent(List<String> lines) {
        for (String line : lines) {
            String[] values = line.split(delimeter);
            Integer storyId = Integer.parseInt(values[0]);
            String storyText = values[1];
            Story story = new Story(storyId, storyText);
            story.setExperience(Integer.parseInt(values[2]));
            if (values.length > 3) {
                for (int i = 3; i < values.length; i+=2) {
                    Hero enemy = new Hero(values[i]);
                    enemy.setHealth(Integer.parseInt(values[i + 1]));
                    if (!values[i + 2].isEmpty()) {
                        enemy.setWeapon(WeaponProvider.get(Integer.parseInt(values[i + 2])));
                        i++;
                    }
                    story.setEnemy(enemy);
                }
            }
            storyList.add(story);
        }
    }

    @Override
    public List<Story> getContent() {
        return storyList;
    }

}