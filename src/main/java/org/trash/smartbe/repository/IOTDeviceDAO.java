package org.trash.smartbe.repository;

import org.trash.smartbe.model.IOTDevice;

import java.util.List;

public interface IOTDeviceDAO {
    List<IOTDevice> findAll();
    IOTDevice findById(String deviceId);
    IOTDevice save(IOTDevice iotDevice);
    void deleteById(String deviceId);
}
