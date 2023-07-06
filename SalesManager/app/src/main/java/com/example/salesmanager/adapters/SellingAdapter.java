package com.example.salesmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.salesmanager.R;
import com.example.salesmanager.models.Category;

import java.util.List;

public class SellingAdapter extends BaseAdapter {
    Context context;
    List<Category> categoryList;
    int layout;

    public SellingAdapter(Context context, List<Category> categoryList, int layout) {
        this.context = context;
        this.categoryList = categoryList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categoryList.get(position).getIdCategory();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, parent, false);

            holder = new ViewHolder();
            holder.txtCategoryName = convertView.findViewById(R.id.txtCategoryName);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Category category = (Category) getItem(position);
        holder.txtCategoryName.setText(category.getNameCategory());
        return convertView;
    }
    public static class ViewHolder {
        TextView txtCategoryName;
    }
}
