package com.cleveroad.adaptivetablelayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 * Common base class of common implementation for an {@link AdaptiveTableAdapter} that
 * can be used in {@link AdaptiveTableLayout}.
 */
public abstract class LinkedAdaptiveTableAdapter<VH extends ViewHolder> implements AdaptiveTableAdapter<VH> {
    protected static final int[] COLORS = new int[]{
            0xffe62a10, 0xffe91e63, 0xff9c27b0, 0xff673ab7, 0xff3f51b5,
            0xff5677fc, 0xff03a9f4, 0xff00bcd4, 0xff009688, 0xff259b24,
            0xff8bc34a, 0xffcddc39, 0xffffeb3b, 0xffffc107, 0xffff9800, 0xffff5722};
    protected boolean mIsRtl;
    /**
     * Set with observers
     */
    @NonNull
    private final List<AdaptiveTableDataSetObserver> mAdaptiveTableDataSetObservers = new ArrayList<>();

    /**
     * Need to throw item click action
     */
    @Nullable
    private OnItemClickListener mOnItemClickListener;

    /**
     * Need to throw long item click action
     */
    @Nullable
    private OnItemLongClickListener mOnItemLongClickListener;

    @Override
    @Nullable
    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    @Override
    public void setOnItemClickListener(@Nullable OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    @Nullable
    public OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    @Override
    public void setOnItemLongClickListener(@Nullable OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    public List<AdaptiveTableDataSetObserver> getAdaptiveTableDataSetObservers() {
        return mAdaptiveTableDataSetObservers;
    }

    /**
     * {@inheritDoc}
     *
     * @param observer the object that gets notified when the data set changes.
     */
    @Override
    public void registerDataSetObserver(@NonNull AdaptiveTableDataSetObserver observer) {
        mAdaptiveTableDataSetObservers.add(observer);
    }

    /**
     * {@inheritDoc}
     *
     * @param observer the object to unregister.
     */
    @Override
    public void unregisterDataSetObserver(@NonNull AdaptiveTableDataSetObserver observer) {
        mAdaptiveTableDataSetObservers.remove(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDataSetChanged() {
        for (AdaptiveTableDataSetObserver observer : mAdaptiveTableDataSetObservers) {
            observer.notifyDataSetChanged();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param rowIndex    the row index
     * @param columnIndex the column index
     */
    @Override
    public void notifyItemChanged(int rowIndex, int columnIndex) {
        for (AdaptiveTableDataSetObserver observer : mAdaptiveTableDataSetObservers) {
            observer.notifyItemChanged(rowIndex, columnIndex);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param rowIndex the row index
     */
    @Override
    public void notifyRowChanged(int rowIndex) {
        for (AdaptiveTableDataSetObserver observer : mAdaptiveTableDataSetObservers) {
            observer.notifyRowChanged(rowIndex);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param columnIndex the column index
     */
    @Override
    public void notifyColumnChanged(int columnIndex) {
        for (AdaptiveTableDataSetObserver observer : mAdaptiveTableDataSetObservers) {
            observer.notifyColumnChanged(columnIndex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyLayoutChanged() {
        for (AdaptiveTableDataSetObserver observer : mAdaptiveTableDataSetObservers) {
            observer.notifyLayoutChanged();
        }
    }

    @Override
    public void onViewHolderRecycled(@NonNull VH viewHolder) {
        //do something
    }

    public boolean isRtl() {
        return mIsRtl;
    }

    public void setRtl(boolean rtl) {
        mIsRtl = rtl;
    }
}
