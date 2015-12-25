package com.example.test_42_label_apkname.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 绘制工具类
 * 功能：相对屏幕百分比转化为实际像素值，罩子层的实际显示区域， 不能分辨率下的等比转换
 * @author jiangxuwen
 * 
 */
public class DrawUtils {
	public static float sDensity = 1.0f;
	public static int sDensityDpi;
	public static int sWidthPixels;
	public static int sHeightPixels;
	public static float sFontDensity;

	public static int sStatusHeight; // 平板中底边的状态栏高度
	
	private static boolean sShowInLauncher = true;

	private static boolean sIsNeedUpdate = false; //等杀完再更新数值, false为默认，true时就代码启动杀

	/**
	 * dip/dp转像素
	 * 
	 * @param dipValue
	 *            dip或 dp大小
	 * @return 像素值
	 */
	public static int dip2px(float dipVlue) {
		return (int) (dipVlue * sDensity + 0.5f);
	}

	/**
	 * 像素转dip/dp
	 * 
	 * @param pxValue
	 *            像素大小
	 * @return dip值
	 */
	public static int px2dip(float pxValue) {
		final float scale = sDensity;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * sp 转 px
	 * 
	 * @param spValue
	 *            sp大小
	 * @return 像素值
	 */
	public static int sp2px(float spValue) {
		final float scale = sDensity;
		return (int) (scale * spValue);
	}

	/**
	 * px转sp
	 * 
	 * @param pxValue
	 *            像素大小
	 * @return sp值
	 */
	public static int px2sp(float pxValue) {
		final float scale = sDensity;
		return (int) (pxValue / scale);
	}

	/**
	 * <br>注意:强制reset
	 * @param context
	 */
	public static void resetForce(Context context) {
		if (context != null && null != context.getResources()) {
			Resources res = context.getResources();
			DisplayMetrics metrics = res.getDisplayMetrics();
			sDensity = metrics.density;
			sFontDensity = metrics.scaledDensity;
			sWidthPixels = metrics.widthPixels;
			sHeightPixels = metrics.heightPixels;
			sDensityDpi = metrics.densityDpi;
		}
	}

	/**
	 * 减速的三次曲线插值
	 * 
	 * @param begin
	 * @param end
	 * @param t
	 *            应该位于[0, 1]
	 * @return
	 */
	public static float easeOut(float begin, float end, float t) {
		t = 1 - t;
		return begin + (end - begin) * (1 - t * t * t);
	}

	public static Bitmap createBitmapFromDrawable(final Drawable drawable) {

		if (drawable == null) {
			return null;
		}

		Bitmap bitmap = null;
		final int intrinsicWidth = drawable.getIntrinsicWidth();
		final int intrinsicHeight = drawable.getIntrinsicHeight();

		try {
			Config config = drawable.getOpacity() != PixelFormat.OPAQUE
					? Bitmap.Config.ARGB_8888
					: Bitmap.Config.RGB_565;
			bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config);
		} catch (OutOfMemoryError e) {
			return null;
		}

		Canvas canvas = new Canvas(bitmap);
		// canvas.setBitmap(bitmap);
		drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
		drawable.draw(canvas);
		canvas = null;
		return bitmap;
	}
	
	public static int getStatusBarHeight() {
		Resources resources = Resources.getSystem();
		int resourceId = Resources.getSystem().getIdentifier(
				"status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			return resources.getDimensionPixelSize(resourceId);
		}
		return 0;
	}
	
	public static int getNavigationBarHeight() {
		Resources resources = Resources.getSystem();
		int resourceId = resources.getIdentifier("navigation_bar_height",
				"dimen", "android");
		if (resourceId > 0) {
			return resources.getDimensionPixelSize(resourceId);
		}
		return 0;
	}

	@SuppressLint("NewApi")
	public static int getWindowWidth(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Point point = new Point();
		windowManager.getDefaultDisplay().getSize(point);
		return point.x;
	}
	
	/**
	 * 获取字体绘制高度
	 * @param paint
	 * @return
	 */
	public static float getFontHeight(Paint paint) {
		FontMetrics fm = paint.getFontMetrics();
		return (float) Math.ceil(fm.descent - fm.ascent);
	}
	
	/**
	 * 用于判断悬浮窗隐藏到时钟在特定的手机不能显示，现全部开启该功能
	 * @param modelName
	 * @return
	 */
	public static boolean isFloatWindowCanNotHide() {
		boolean ret = false;
		String modelName = android.os.Build.MODEL;
		if (modelName.equalsIgnoreCase("IM-A800S")) { // 对特定机型适配
			ret = true;
		}
		return ret;
	}
	
	public static void setShowInLauncher(boolean isShow) {
		sShowInLauncher = isShow;
	}
	
	public static boolean getShowInLauncher() {
		return sShowInLauncher;
	}
	
	public static boolean getNeedUpdate() {
		return sIsNeedUpdate;
	}
	
}
