package com.example.mab_group_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.core.view.LayoutInflaterFactory;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> nutrilist;
    private HashMap<String,List<String>> nutrisublist;

    public ExpandableListViewAdapter(Context context,List<String> nutrilist, HashMap<String,List<String>> nutrisublist){
        this.context = context;
        this.nutrilist = nutrilist;
        this.nutrisublist = nutrisublist;
    }

    @Override
    public int getGroupCount(){
        return this.nutrilist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return this.nutrisublist.get(this.nutrilist.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition){
        return this.nutrilist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition){
        return this.nutrisublist.get(this.nutrilist.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition){
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition){
        return childPosition;
    }

    @Override
    public boolean hasStableIds(){
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isexpanded, View convertview, ViewGroup parent){
        String nutrilisttitle = (String) getGroup(groupPosition);

        if (convertview==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.nutri_list, null);
        }

        TextView nutritv = convertview.findViewById(R.id.nutri_tv);
        nutritv.setText(nutrilisttitle);
        
        convertview.setBackgroundColor(Color.BLACK);

        return convertview;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertview, ViewGroup parent){
        String topiclisttitle = (String) getChild(groupPosition, childPosition);

        if (convertview==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.nutri_sub_list, null);
        }

        TextView nutrisuvtitle =  convertview.findViewById(R.id.nutri_sub);
        nutrisuvtitle.setText(topiclisttitle);

        return convertview;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
