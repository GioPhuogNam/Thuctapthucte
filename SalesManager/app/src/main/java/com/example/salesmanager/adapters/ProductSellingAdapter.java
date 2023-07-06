package com.example.salesmanager.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesmanager.R;
import com.example.salesmanager.activities.user.CartActivity;
import com.example.salesmanager.models.Product;

import java.util.List;

public class ProductSellingAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Product> productList;

    public ProductSellingAdapter(Context context, int layout, List<Product> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productList.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, parent, false);

            holder = new ViewHolder();
            holder.productImage = view.findViewById(R.id.productImage);
            holder.txtProductName = view.findViewById(R.id.txtProductName);
            holder.txtProductPrice = view.findViewById(R.id.txtProductPrice);
            holder.btnAddToShoppingCart = view.findViewById(R.id.btnAddToShoppingCart);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Product product = (Product) getItem(position);

        byte[] productImage = product.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);

        holder.productImage.setImageBitmap(bitmap);
        holder.txtProductName.setText(product.getName());
        holder.txtProductPrice.setText("" + product.getPrice());

        holder.btnAddToShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean add = CartActivity.addItem(product.getId(), 1);
                if (add) {
                    Toast.makeText(v.getContext(), "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(v.getContext(), "Thêm vào giỏ hàng Không thành công", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private class ViewHolder {
        ImageView productImage;
        TextView txtProductName, txtProductPrice;
        Button btnAddToShoppingCart;
    }
}
