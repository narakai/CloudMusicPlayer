package cn.qhung.musicplayer.music.discovery.repository.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import cn.qhung.musicplayer.music.discovery.repository.MusicDataSource;
import cn.qhung.musicplayer.net.entities.Banner;
import cn.qhung.musicplayer.net.entities.MusicPlayList;
import cn.qhung.musicplayer.utils.Log;
import cn.qhung.musicplayer.utils.schedulers.BaseSchedulerProvider;
import rx.Observable;
import rx.functions.Func1;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by qhung on 2017/3/20.
 */

public class LocalMusicDateSource implements MusicDataSource {

    private static LocalMusicDateSource INSTANCE;
    private final BaseSchedulerProvider mSchedulerProvider;
    private final Context mContext;
    private final BriteDatabase mDatabaseHelper;

    private LocalMusicDateSource(@NotNull Context context,
                                 @NotNull BaseSchedulerProvider schedulerProvider) {
        this.mContext = checkNotNull(context);
        this.mSchedulerProvider = checkNotNull(schedulerProvider);

        MusicDbHelper dbHelper = new MusicDbHelper(context);
        SqlBrite sqlBrite = SqlBrite.create();
        mDatabaseHelper = sqlBrite.wrapDatabaseHelper(dbHelper, schedulerProvider.io());
        mDatabaseHelper.setLoggingEnabled(true);
    }

    public static LocalMusicDateSource getInstance(@NotNull Context context,
                                                   @NotNull BaseSchedulerProvider schedulerProvider) {
        if (INSTANCE == null) {
            INSTANCE = new LocalMusicDateSource(context, schedulerProvider);
        }
        return INSTANCE;
    }

    public void saveBanner(List<Banner> banners) {
        final Date date = new Date(System.currentTimeMillis());

        BriteDatabase.Transaction transaction = mDatabaseHelper.newTransaction();
        for (Banner banner : banners) {
            saveBanner(banner, date);
        }
        transaction.markSuccessful();
        transaction.end();
    }

    private void saveBanner(Banner banner, Date date) {
        checkNotNull(banner);
        checkNotNull(date);
        ContentValues values = new ContentValues();
        values.put(BannerPersistenceContract.BannerEntry.COLUMN_NAME_ENTRY_ID, banner.getId());
        values.put(BannerPersistenceContract.BannerEntry.COLUMN_NAME_TIME, date.toString());
        values.put(BannerPersistenceContract.BannerEntry.COLUMN_NAME_PIC_URL, banner.getPicUrl());
        values.put(BannerPersistenceContract.BannerEntry.COLUMN_NAME_LINK_URL, banner.getLinkUrl());
        mDatabaseHelper.insert(BannerPersistenceContract.BannerEntry.TABLE_NAME,
                values, SQLiteDatabase.CONFLICT_NONE);
    }

    @NonNull
    private Banner createBannerEntity(@NonNull Cursor c) {
        int itemId = c.getInt(
                c.getColumnIndexOrThrow(BannerPersistenceContract.BannerEntry.COLUMN_NAME_ENTRY_ID));
        String time = c.getString(
                c.getColumnIndexOrThrow(BannerPersistenceContract.BannerEntry.COLUMN_NAME_TIME));
        String picUrl = c.getString(
                c.getColumnIndexOrThrow(BannerPersistenceContract.BannerEntry.COLUMN_NAME_PIC_URL));
        String linkUrl = c.getString(
                c.getColumnIndexOrThrow(
                        BannerPersistenceContract.BannerEntry.COLUMN_NAME_LINK_URL));
        Log.QLog().d(itemId);
        return new Banner(itemId, linkUrl, picUrl);
    }


    @Override
    public Observable<List<Banner>> getBanner() {
        return getBanner(new Date(System.currentTimeMillis()));
    }

    @Override
    public Observable<List<MusicPlayList>> getMusicCollection() {
        return null;
    }

    @NonNull
    private Observable<List<Banner>> getBanner(@NonNull Date date) {
        checkNotNull(date);
        final String[] banner = {
                BannerPersistenceContract.BannerEntry.COLUMN_NAME_ENTRY_ID,
                BannerPersistenceContract.BannerEntry.COLUMN_NAME_TIME,
                BannerPersistenceContract.BannerEntry.COLUMN_NAME_PIC_URL,
                BannerPersistenceContract.BannerEntry.COLUMN_NAME_LINK_URL
        };
        String sql = String.format("SELECT %s FROM %s WHERE %s='%s'",
                TextUtils.join(",", banner),
                BannerPersistenceContract.BannerEntry.TABLE_NAME,
                BannerPersistenceContract.BannerEntry.COLUMN_NAME_TIME, date.toString());
        Cursor cursor = mDatabaseHelper.query(sql);
        return Observable.just(cursor)
                .subscribeOn(mSchedulerProvider.io())
                .unsubscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .map(new Func1<Cursor, List<Banner>>() {
                    @Override
                    public List<Banner> call(Cursor cursor) {
                        List<Banner> list = new ArrayList<>();
                        if (cursor.moveToFirst()) {
                            do {
                                Banner b = createBannerEntity(cursor);
                                list.add(b);
                            } while (cursor.moveToNext());
                        }
                        Log.QLog().d(list.size());
                        cursor.close();
                        return list;
                    }
                });

    }
}
