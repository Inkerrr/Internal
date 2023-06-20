package com.example.internal;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.internal.R;


public class AccountFragment extends Fragment {


    Button registrationButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,container, false);

        registrationButton = view.findViewById(R.id.profile_reg_button);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileReg.class); // THIS PART IS IMPORTANT(IT SHOWS HOW YOU CAN ANOTHER ACTIVITY)
                startActivity(intent);
            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);

    }
}