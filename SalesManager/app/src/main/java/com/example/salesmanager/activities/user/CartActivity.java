package com.example.salesmanager.activities.user;

import com.sun.mail.imap.protocol.Item;

import java.util.ArrayList;
import java.util.List;

public class CartActivity {
    public static List<Item> listItem;
    public static void removeByProductId(int id) {
        for (Item item : listItem) {
            if (item.product_id == id) {
                listItem.remove(item);
                return;
            }
        }
    }

    public static void setQuantityByProductId(int id, int quantity) {
        for (int i = 0; i < listItem.size(); i++) {
            if (listItem.get(i).product_id == id) {
                listItem.get(i).setQuantity(quantity);
                return;
            }
        }
    }

    public static class Item {
        private int product_id;
        private int quantity;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Item(int product_id, int quantity) {
            this.product_id = product_id;
            this.quantity = quantity;
        }
    }
    // Them vao gio hang
    public static boolean addItem(int product_id, int quantity) {
        if (listItem == null) {
            listItem = new ArrayList<>();
        }

        for (Item item : listItem) {
            if (item.product_id == product_id) {
                item.quantity += quantity;
                return true;
            }
        }
        // Neu chua co product id thi them moi
        Item item = new Item(product_id, quantity);
        listItem.add(item);
        return true;
    }
    // Lay ra thong tin san pham
    public static Item getItem(int product_id) {
        for (Item item: listItem) {
            if (item.product_id == product_id) {
                return item;
            }
        }
        return null;
    }

    public static boolean deleteItem(int product_id) {
        for (Item item: listItem) {
            if (item.product_id == product_id) {
                listItem.remove(item);
                return true;
            }
        }
        return false;
    }

    public static int sum() {
        int s = 0;
        for (Item item : listItem) {
            s += item.quantity;
        }
        return s;
    }

    public static int quantity_product() {
        return listItem.size();
    }

}
