package org.trash.smartbe.repository;

import org.trash.smartbe.model.IOTDevice;

import java.util.List;

public interface IOTDeviceDAO {
    List<IOTDevice> findAll();
    IOTDevice findById(Long deviceId);
    IOTDevice save(IOTDevice iotDevice);
    IOTDevice update(Long deviceId, IOTDevice updatedIOTDevice);
    void deleteById(Long deviceId);
}
