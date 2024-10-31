package Depot;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
  Map<String, Parcel> parcelMap;
  
  public ParcelMap()
  {
    parcelMap = new HashMap<>();
  }

  public Parcel getParcel(String id)
  {
    return parcelMap.get(id);
  }

  public void addParcel(Parcel parcel)
  {
    parcelMap.put(parcel.getId(), parcel);
  }
}
