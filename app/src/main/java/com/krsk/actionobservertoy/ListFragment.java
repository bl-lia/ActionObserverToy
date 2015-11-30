package com.krsk.actionobservertoy;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by bl-lia on 11/27/15.
 */
public class ListFragment extends Fragment {

    @Bind(R.id.list_item) RecyclerView mItemList;

    @Nullable ListAdapter mListAdapter;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        initView();
        resetList();
        return view;
    }

    private void initView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mItemList.setLayoutManager(linearLayoutManager);

        mListAdapter = new ListAdapter();
        mItemList.setAdapter(mListAdapter);

        mListAdapter.getItemPublishSubject().subscribe(new Action1<Item>() {
            @Override
            public void call(Item item) {
                Log.d(ListFragment.class.getSimpleName(), String.format("Item: %s", item.name));
            }
        });
    }

    private void resetList() {
        if (mListAdapter == null) {
            return;
        }

        final List<Item> items = createItems();
        mListAdapter.reset(items);
    }

    private List<Item> createItems() {
        final List<Item> items = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            items.add(new Item("AAAAA"));
            items.add(new Item("BBBBB"));
            items.add(new Item("CCCCC"));
            items.add(new Item("DDDDD"));
            items.add(new Item("EEEEE"));
            items.add(new Item("FFFFF"));
            items.add(new Item("GGGGG"));
            items.add(new Item("HHHHH"));
            items.add(new Item("IIIII"));
            items.add(new Item("JJJJJ"));
            items.add(new Item("KKKKK"));
            items.add(new Item("LLLLL"));
            items.add(new Item("MMMMM"));
            items.add(new Item("NNNNN"));
            items.add(new Item("OOOOO"));
            items.add(new Item("PPPPP"));
            items.add(new Item("QQQQQ"));
            items.add(new Item("RRRRR"));
            items.add(new Item("SSSSS"));
            items.add(new Item("TTTTT"));
            items.add(new Item("UUUUU"));
            items.add(new Item("VVVVV"));
            items.add(new Item("WWWWW"));
            items.add(new Item("XXXXX"));
            items.add(new Item("YYYYY"));
            items.add(new Item("ZZZZZ"));
        }

        return items;
    }
}
