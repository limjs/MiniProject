package com.example.tacademy.miniproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tacademy.miniproject.data.NetworkResult;
import com.example.tacademy.miniproject.data.User;
import com.example.tacademy.miniproject.manager.NetworkManager;
import com.example.tacademy.miniproject.manager.NetworkRequest;
import com.example.tacademy.miniproject.request.FriendListRequest;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    ListView listView;
    ArrayAdapter<User> mAdapter;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ArrayAdapter<User>(getContext(), android.R.layout.simple_list_item_1);
        FriendListRequest request = new FriendListRequest(getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<User>>>(){
            @Override
            public void onFail(NetworkRequest<NetworkResult<List<User>>> request, int errorCode, String errorMessage, Throwable e) {

            }

            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<User>>> request, NetworkResult<List<User>> result) {
                List<User> users = result.getResult();
                mAdapter.addAll(users);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
