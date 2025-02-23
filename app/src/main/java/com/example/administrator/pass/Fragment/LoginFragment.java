package com.example.administrator.pass.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.pass.R;


public class LoginFragment extends Fragment  {
	private AutoCompleteTextView mUserView;
	private EditText mPasswordView;
	private Button mLoginBtn;
	private Button mRegisterBtn;
	private mLoginTask mLoginTask;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_login,container,false);
		initView(root);



		return root;
	}

	private void initView(View root) {
		mUserView = (AutoCompleteTextView) root.findViewById(R.id.user);
		mPasswordView = (EditText) root.findViewById(R.id.password);
		mLoginBtn = (Button) root.findViewById(R.id.btn_login);
		mRegisterBtn = (Button) root.findViewById(R.id.btn_register);
		mLoginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				attemptLogin();
			}
		});
		mRegisterBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getActivity().getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				fragmentTransaction.replace(R.id.LoginFrameLayout,new registerFragment());
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});
	}

	private Boolean attemptLogin() {
		if (mLoginTask != null){
			mLoginTask = null;
		}
		String user = mUserView.getText().toString();
		String password = mPasswordView.getText().toString();
		mUserView.setError(null);
		mPasswordView.setError(null);
		if(user.length() == 0){
			mUserView.setError(getString(R.string.error_invalid_user));
			return false;
		}
		if(password.length() <= 6){
			mPasswordView.setError(getString(R.string.error_invalid_user));
			return false;
		}
		mLoginTask = new mLoginTask(user,password);
		mLoginTask.execute((Void[]) null);
		return true;
	}

	public void setToast(String toast) {
		Toast.makeText(getActivity(),toast,Toast.LENGTH_LONG).show();
	}


	public class mLoginTask extends AsyncTask<Void, Void, Boolean>{
		private String User;
		private String password;
		mLoginTask(String User,String password){
			this.User = User;
			this.password = password;

		}

		@Override
		protected Boolean doInBackground(Void... params) {

			return true;
		}

		@Override
		protected void onPostExecute(Boolean aBoolean) {
			if(aBoolean){
				setToast("登陆成功");// TODO: 11/8 0008
			}
		}
	}




}
