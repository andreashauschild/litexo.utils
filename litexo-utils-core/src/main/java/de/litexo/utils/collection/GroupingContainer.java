package de.litexo.utils.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * www.litexo.de
 * 
 * @author Andreas Hauschild
 */
public class GroupingContainer<T> {

    private List<T> items;

    public List<List<T>> getGroupsByMaxItemLimit(int itemLimitPerGroup) {

        List<List<T>> result = new ArrayList<>();
        int currentIndex = 0;
        for (int i = 0; i < this.getItems().size(); i++) {




            if (!indexExists(result, currentIndex)) {
                result.add(currentIndex, new ArrayList<>());
            }
            result.get(currentIndex).add(this.getItems().get(i));

            if (result.get(currentIndex).size() >= itemLimitPerGroup) {
                currentIndex++;
            }


        }
        return result;
    }

    public List<List<T>> getGroupsByMaxGroupLimit(int maxGroups) {

        List<List<T>> result = new ArrayList<>();

        int itemCount = 0;
        for (int i = 0; i < this.getItems().size(); i++) {
            if (itemCount / maxGroups != 0) {
                itemCount = 0;
            }
            if (!indexExists(result, itemCount)) {
                result.add(i, new ArrayList<>());
            }
            result.get(itemCount).add(this.getItems().get(i));
            itemCount++;
        }
        return result;
    }

    public List<T> getItems() {

        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        return this.items;
    }

    public GroupingContainer<T> addItems(List<T> items) {

        getItems().addAll(items);
        return this;
    }

    public GroupingContainer<T> addItem(T item) {

        getItems().add(item);
        return this;
    }

    private boolean indexExists(final List list, final int index) {

        return index >= 0 && index < list.size();
    }
}
