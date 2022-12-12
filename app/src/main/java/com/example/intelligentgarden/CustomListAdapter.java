package com.example.intelligentgarden;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<Product> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext, ArrayList<Product> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        Log.v("CONST", ""+listData.get(0));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Log.v("START GETVIEW", "");
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_custom_list_view, null);
            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.textId);
            holder.name = (TextView) convertView.findViewById(R.id.textName);
            holder.type = (TextView) convertView.findViewById(R.id.textType);
            holder.qty = (TextView) convertView.findViewById(R.id.textNumber);
            holder.price = (TextView) convertView.findViewById(R.id.textPrice);
            convertView.setTag(holder);
            Log.v("END GETVIEW", "");


        } else {
            holder = (ViewHolder) convertView.getTag();
            Log.v("position",""+position);
        }

        if(position % 2 == 0){
            convertView.setBackgroundColor(Color.rgb(150,245,170));
        }
        Log.v("START CUSTOM","");
        Product product = this.listData.get(position);
        holder.id.setText(""+product.getId());
        holder.name.setText(product.getName());
        holder.type.setText(product.getType());
        holder.qty.setText(""+product.getQty());
        holder.price.setText(""+product.getPrice());
        Log.v("CUSTOM",""+product.getName()+" "+product.getType());

        return convertView;
    }

    static class ViewHolder {
        TextView id;
        TextView name;
        TextView type;
        TextView qty;
        TextView price;
    }

    public int getCount() {
        return (listData!=null)?listData.size():0;
    }
    public Object getItem(int position) {
        return listData.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
}