package tom.android.fixtime;

import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class FixTheTimeActivity extends ListActivity {
	private TimeDataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		datasource = new TimeDataSource(this);
		datasource.open();

		List<Date> values = datasource.getAllDates();

		ArrayAdapter<Date> adapter = new ArrayAdapter<Date>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}
	
	public void onAddClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<Date> adapter = (ArrayAdapter<Date>) getListAdapter();
		Date date = new Date();
		datasource.createTime(date);
		
		adapter.add(date);
		adapter.notifyDataSetChanged();
	}

	public void onRemoveClick(View view) {

	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

}