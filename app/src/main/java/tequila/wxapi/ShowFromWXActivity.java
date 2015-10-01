package tequila.wxapi;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import com.tangxinli.android.tequila.R;
import com.tangxinli.android.tequila.utils.LogUtils;

public class ShowFromWXActivity extends Activity {
    public static final String TAG = "ShowFromWXActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        LogUtils.d(TAG, "Activity Created");
		setContentView(R.layout.show_from_wx);
		initView();
	}

	private void initView() {
        LogUtils.d(TAG, "initiating view");

		final String title = getIntent().getStringExtra(Constants.ShowMsgActivity.STitle);
		final String message = getIntent().getStringExtra(Constants.ShowMsgActivity.SMessage);
		final byte[] thumbData = getIntent().getByteArrayExtra(Constants.ShowMsgActivity.BAThumbData);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);

		if (thumbData != null && thumbData.length > 0) {
			ImageView thumbIv = new ImageView(this);
			thumbIv.setImageBitmap(BitmapFactory.decodeByteArray(thumbData, 0, thumbData.length));
			builder.setView(thumbIv);
		}

		builder.show();
	}
}
