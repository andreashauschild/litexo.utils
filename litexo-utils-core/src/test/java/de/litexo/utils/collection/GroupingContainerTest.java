package de.litexo.utils.collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupingContainerTest {

    @DisplayName("Test max number of groups with more items as groups")
    @Test
    public void testMaxGroups() {

        GroupingContainer<String> groups = new GroupingContainer<>();
        List<List<String>> result = groups.addItems(Arrays.asList("1", "2", "3", "4", "5")).getGroupsByMaxGroupLimit(3);

        assertEquals(3,result.size());
        assertEquals("1",result.get(0).get(0));
        assertEquals("2",result.get(1).get(0));
        assertEquals("3",result.get(2).get(0));
        assertEquals("4",result.get(0).get(1));
        assertEquals("5",result.get(1).get(1));
    }

    @DisplayName("Test max number of groups with less items as groups")
    @Test
    public void testMaxGroupsLessItemsAsGroups() {

        GroupingContainer<String> groups = new GroupingContainer<>();
        List<List<String>> result = groups.addItems(Arrays.asList("1", "2", "3")).getGroupsByMaxGroupLimit(5);

        assertEquals(3,result.size());
        assertEquals("1",result.get(0).get(0));
        assertEquals("2",result.get(1).get(0));
        assertEquals("3",result.get(2).get(0));

    }

    @DisplayName("Test max number items per group. Limit:1 -> 3 Items = 3 Groups")
    @Test
    public void testMaxGroupsByItemLimit() {

        GroupingContainer<String> groups = new GroupingContainer<>();
        List<List<String>> result = groups.addItems(Arrays.asList("1", "2", "3")).getGroupsByMaxItemLimit(1);

        assertEquals(3,result.size());
        assertEquals("1",result.get(0).get(0));
        assertEquals("2",result.get(1).get(0));
        assertEquals("3",result.get(2).get(0));

    }

    @DisplayName("Test max number items per group. Limit:2 -> 5 Items = 3 Groups")
    @Test
    public void testGroupsByItemLimit() {

        GroupingContainer<String> groups = new GroupingContainer<>();
        List<List<String>> result = groups.addItems(Arrays.asList("1", "2", "3","4","5")).getGroupsByMaxItemLimit(2);

        assertEquals(3,result.size());
        assertEquals("1",result.get(0).get(0));
        assertEquals("2",result.get(0).get(1));
        assertEquals("3",result.get(1).get(0));
        assertEquals("4",result.get(1).get(1));
        assertEquals("5",result.get(2).get(0));

    }
}