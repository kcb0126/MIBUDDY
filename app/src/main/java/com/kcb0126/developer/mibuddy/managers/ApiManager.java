package com.kcb0126.developer.mibuddy.managers;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.kcb0126.developer.mibuddy.models.GroupModel;
import com.kcb0126.developer.mibuddy.models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by developer on 3/13/2018.
 */

public class ApiManager {
    private String baseUrl = "http://192.168.3.25:8000/";

    private String loginUrl = baseUrl + "login/";
    private String signupUrl = baseUrl + "signup/";

    private String profileUrl = baseUrl + "profile/";

    private String createUrl = baseUrl + "create/";
    private String groupListUrl = baseUrl + "groups/";

    private static ApiManager mInstance = null;

    public static ApiManager instance() {
        if(mInstance == null) {
            mInstance = new ApiManager();
        }
        return mInstance;
    }

    public ApiManager() {

    }

    public void callApi(final String url, final Map<String, Object> params, final CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL urlObj = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection)urlObj.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");
                    OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
                    StringBuilder builder = new StringBuilder();
                    for(String key : params.keySet()) {
                        builder.append("&").append(key).append("=").append(params.get(key));
                    }
                    osw.write(builder.substring(1));
                    osw.close();
                    connection.connect();
                    final int code = connection.getResponseCode();
                    BufferedReader br;
                    if(code == HttpURLConnection.HTTP_OK) {
                        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    } else {
                        br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    }
                    final StringBuilder sb = new StringBuilder();
                    String line = null;
                    while((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    br.close();
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Object json = new JSONTokener(sb.toString()).nextValue();
                                if(code == HttpURLConnection.HTTP_OK) {
                                    callBack.success(json);
                                } else {
                                    callBack.fail((JSONObject)json);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String getErrorDetail(JSONObject data) {
        Iterator<?> keys = data.keys();
        StringBuilder errorMsg = new StringBuilder("");
        while (keys.hasNext()) {
            String key = (String)keys.next();
            try {
                Object detail = data.get(key);
                if(detail instanceof JSONArray) {
                    errorMsg.append(" ").append(key).append(": ").append(((JSONArray) detail).get(0));
                } else {
                    errorMsg.append(" ").append(key).append(": ").append(detail);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return errorMsg.toString();
    }

    public void login(final Context context, String email, String password, final Runnable success) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        callApi(loginUrl, params, new CallBack() {
            @Override
            public void success(Object data) {
                try {
                    String token = (String)((JSONObject)data).get("token");
                    PreferenceManager.instance().putToken(context, token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                success.run();
            }

            @Override
            public void fail(JSONObject data) {
                Toast.makeText(context, "Login failed." + getErrorDetail(data), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void signup(final Context context, String email, String password, String username, String gender, int age, String nationality, final Runnable success) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("username", username);
        params.put("gender", gender);
        params.put("age", age);
        params.put("nationality", nationality);
        callApi(signupUrl, params, new CallBack() {
            @Override
            public void success(Object data) {
                success.run();
            }

            @Override
            public void fail(JSONObject data) {
                Toast.makeText(context, "Signup failed." + getErrorDetail(data), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void profile(final Context context, final Runnable success) {
        HashMap<String, Object> params = new HashMap<>();
        String token = PreferenceManager.instance().getToken(context);
        params.put("token", token);
        callApi(profileUrl, params, new CallBack() {
            @Override
            public void success(Object data) {
                UserModel.instance().parseFromJSON((JSONObject) data);
                success.run();
            }

            @Override
            public void fail(JSONObject data) {
                Toast.makeText(context, "Cannot show your profile." + getErrorDetail(data), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createGroup(final Context context, String name, String community, final Runnable success) {
        HashMap<String, Object> params = new HashMap<>();
        String token = PreferenceManager.instance().getToken(context);
        params.put("token", token);
        params.put("name", name);
        params.put("community", community);
        callApi(createUrl, params, new CallBack() {
            @Override
            public void success(Object data) {
                success.run();
            }

            @Override
            public void fail(JSONObject data) {
                Toast.makeText(context, "Cannot create a new group." + getErrorDetail(data), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void groupList(Context context, String community, String keyword, final GroupListCallBack callBack) {
        HashMap<String, Object> params = new HashMap<>();
        String token = PreferenceManager.instance().getToken(context);
        params.put("token", token);
        params.put("community", community);
        params.put("keyword", keyword);
        callApi(groupListUrl, params, new CallBack() {
            @Override
            public void success(Object data) {
                JSONArray groupsArray = (JSONArray)data;
                ArrayList<GroupModel> groups = new ArrayList<>();
                for(int i = 0; i < groupsArray.length(); i++) {
                    try {
                        GroupModel group = GroupModel.parseFromJSON(groupsArray.getJSONObject(i));
                        if(group != null) {
                            groups.add(group);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callBack.success(groups);
            }

            @Override
            public void fail(JSONObject data) {

            }
        });
    }

    public interface CallBack {
        void success(Object data);
        void fail(JSONObject data);
    }

    public interface GroupListCallBack {
        void success(ArrayList<GroupModel> groups);
    }
}
