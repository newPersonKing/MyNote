package com.gy.mynote.control;

import java.util.ArrayList;

import com.gy.mynote.bean.Notepad;
import com.gy.mynote.R;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class NoteAdapter extends BaseAdapter{

	LayoutInflater inflater;
	ArrayList<Notepad> array;
	 public NoteAdapter(LayoutInflater inf, ArrayList<Notepad> arry){
		this.inflater=inf;
		this.array=arry;
	}
	@Override
	public int getCount() {
		return array.size();
	}

	@Override
	public Object getItem(int position) {
		return array.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if(convertView==null){
			vh=new ViewHolder();
			convertView=inflater.inflate(R.layout.adapter_listview1, null);
			vh.tv1=(TextView) convertView.findViewById(R.id.textView1);
			vh.tv2=(TextView) convertView.findViewById(R.id.textView2);
			convertView.setTag(vh);
		}
		vh=(ViewHolder) convertView.getTag();
		vh.tv1.setText(array.get(position).getTitle());
		vh.tv2.setText(array.get(position).getTimes());		
		return convertView;
	}
	class ViewHolder{
		TextView tv1,tv2;
	}
}
