package org.trash.smartbe.service;

import org.trash.smartbe.model.IOTDevice;

import java.util.List;

public interface IOTDeviceService {
    List<IOTDevice> getAllDevices();
    IOTDevice getDeviceById(String deviceId);
    IOTDevice createDevice(IOTDevice device);
    void deleteDevice(String deviceId);
}
