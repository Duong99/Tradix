package vn.com.nghiemduong.tradix.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class FilterTitle(var titleFilter: String) : Parcelable{
    var isCheck = false

    constructor(parcel: Parcel) : this(parcel.readString().toString()) {
        isCheck = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titleFilter)
        parcel.writeByte(if (isCheck) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterTitle> {
        override fun createFromParcel(parcel: Parcel): FilterTitle {
            return FilterTitle(parcel)
        }

        override fun newArray(size: Int): Array<FilterTitle?> {
            return arrayOfNulls(size)
        }
    }
}