package by.todes.i_sokolov.parmandroidproject;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i_sokolov on 12.08.2014.
 */
public class SlidingMenuFragment extends Fragment implements ExpandableListView.OnChildClickListener {

    private ExpandableListView sectionListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<Section> sectionList = createMenu();

        View view = inflater.inflate(R.layout.slidingmenu_fragment, container, false);
        this.sectionListView = (ExpandableListView) view.findViewById(R.id.slidingmenu_view);
        this.sectionListView.setGroupIndicator(null);

        SectionListAdapter sectionListAdapter = new SectionListAdapter(this.getActivity(), sectionList);
        this.sectionListView.setAdapter(sectionListAdapter);

        this.sectionListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        this.sectionListView.setOnChildClickListener(this);

        int count = sectionListAdapter.getGroupCount();
        for (int position = 0; position < count; position++) {
            this.sectionListView.expandGroup(position);
        }

        return view;
    }

    private List<Section> createMenu() {
        List<Section> sectionList = new ArrayList<Section>();

        Section oGeneralSection = new Section("Настройки входа");
        oGeneralSection.addSectionItem(101,"Добавить сервер", "ic_launcher");
        oGeneralSection.addSectionItem(102, "Удалить сервер", "ic_launcher");
        oGeneralSection.addSectionItem(103,"Список серверов", "ic_launcher");
        oGeneralSection.addSectionItem(104, "Выход", "ic_launcher");
        sectionList.add(oGeneralSection);
        return sectionList;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {

        switch ((int)id) {
            case 101:
                //TODO
                break;
            case 102:
                //TODO
                break;
            case 103:
                //TODO
                break;
            case 104:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
           default:
               break;
        }

        return false;
    }

}