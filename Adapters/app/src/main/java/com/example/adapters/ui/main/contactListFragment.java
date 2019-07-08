package com.example.adapters.ui.main;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.adapters.MainActivity;
import com.example.adapters.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class contactListFragment extends Fragment {

    final String NAME = "name";
    final String NUMBER = "number";
    final String AVATAR = "avatar";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(Names.length);
//        Map<String, Object> m;
//
//        for (int i = 0; i < Names.length; i++) {
//            m = new HashMap<String, Object>();
//
//            m.put(NAME, Names[i]);
//            m.put(NUMBER, PhoneNumbers[i]);
//            m.put(AVATAR, img);
//
//            data.add(m);
//        }
//
//        String[] from = { NAME, NUMBER, AVATAR };
//
//        int[] to = { R.id.contactNameSur, R.id.contactNumber, R.id.contactImg};
//
//        sAdapter = new SimpleAdapter(getActivity(), data, R.layout.contact_item, from, to);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.contact_list, container, false);

        final ListView contacts = root.findViewById(R.id.contacts);

        final Context contactContext = container.getContext();

        Dexter.withActivity((Activity) contactContext)
                .withPermissions(Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        ContentResolver cr = getActivity().getContentResolver();
                        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                                null, null, null, null);

                        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(cur.getCount());

                        String[] from = { NAME, NUMBER, AVATAR };

                        int[] to = { R.id.contactNameSur, R.id.contactNumber, R.id.contactImg};

                        SimpleAdapter sAdapter = new SimpleAdapter(getActivity(), data, R.layout.contact_item, from, to);
                        contacts.setAdapter(sAdapter);

                        Map<String, Object> m;

                        if (cur.getCount() > 0) {
                            while (cur.moveToNext()) {
                                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                                String temp = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));

                                Uri img;

                                if (temp != null) {
                                    img = Uri.parse(temp);
                                } else  {
//                                    Uri imgUri=Uri.parse("android.resource://com.example.adapters/"+R.drawable.image);
                                    img = Uri.parse("android.resource://com.example.adapters/"+R.drawable.contact_image);
                                }

                                m = new HashMap<String, Object>();

                                m.put(NAME, name);
                                m.put(AVATAR, img);

                                if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                            null,
                                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                            new String[]{id}, null);

                                    String phoneNo = "";
                                    while (pCur.moveToNext()) {
                                        phoneNo += pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) + "\n";

                                        sAdapter.notifyDataSetChanged();
                                    }

                                    m.put(NUMBER, phoneNo);
                                    pCur.close();
                                }

                                data.add(m);
                            }
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();



//        View root = inflater.inflate(R.layout.contact_list, container, false);
//
//        final ListView contacts = root.findViewById(R.id.contacts);

//        contacts.setAdapter(sAdapter);

        return root;
    }
}