package donets.danylo.natife.testtask.Data

import com.google.gson.annotations.SerializedName

data class DataObject(
    @SerializedName("images") val images: DataImage
)