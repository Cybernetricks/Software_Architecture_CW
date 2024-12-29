package Depot;

import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
  private Map<String, Parcel> parcelMap;
  
  public ParcelMap()
  {
    parcelMap = new HashMap<>();
  }

  public String toString()
  {
    String parcelsString = "";
    for (Parcel parcel : parcelMap.values()) {
      parcelsString += parcel.getId();
      parcelsString += " - ";
      parcelsString += Float.toString(parcel.getLength());
      parcelsString += ", ";
      parcelsString += Float.toString(parcel.getWidth());
      parcelsString += ", ";
      parcelsString += Float.toString(parcel.getHeight());
      parcelsString += ", ";
      parcelsString += Float.toString(parcel.getWeight());
      parcelsString += ", ";
      parcelsString += Integer.toString(parcel.getDaysInCollection());
      parcelsString += ", ";
      parcelsString += Boolean.toString(parcel.isCollected());
      parcelsString += "\n";
    }
    return parcelsString;
  }

  public Parcel getParcel(String id)
  {
    return parcelMap.get(id);
  }

  public void addParcel(Parcel parcel)
  {
    parcelMap.put(parcel.getId(), parcel);
  }

  public void changeParcelState(String id)
  {
    Parcel parcel = getParcel(id);
    if (parcel.isCollected()) {
      parcel.setCollected(false);
    } else {
      parcel.setCollected(true);
    }
  }
}
