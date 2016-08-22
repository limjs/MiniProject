package com.example.tacademy.miniproject.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tacademy.miniproject.R;
import com.example.tacademy.miniproject.data.NetworkResult;
import com.example.tacademy.miniproject.data.User;
import com.example.tacademy.miniproject.manager.NetworkManager;
import com.example.tacademy.miniproject.manager.NetworkRequest;
import com.example.tacademy.miniproject.manager.PropertyManager;
import com.example.tacademy.miniproject.request.LoginRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    EditText emailView;
    EditText passwordView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        emailView = (EditText)view.findViewById(R.id.edit_email);
        passwordView = (EditText)view.findViewById(R.id.edit_password);

        Button btn = (Button)view.findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final String email = emailView.getText().toString();
                final String password = passwordView.getText().toString();
                LoginRequest request = new LoginRequest(getContext(), email, password, "1234");
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                        User user = result.getResult();
                        Toast.makeText(getContext(), "user id : " + user.getId(), Toast.LENGTH_SHORT).show();
                        PropertyManager.getInstance().setEmail(email);
                        PropertyManager.getInstance().setPassword(password);
                        PropertyManager.getInstance().setRegistrationId("1234");
                        ((SimpleLoginActivity)getActivity()).moveMainActivity();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(getContext(), "error : " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn = (Button)view.findViewById(R.id.btn_signup);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ((SimpleLoginActivity)getActivity()).changeSignup();
            }
        });


        return view;

    }

}
