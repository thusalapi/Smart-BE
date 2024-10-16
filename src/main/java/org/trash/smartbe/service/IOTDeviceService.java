package org.trash.smartbe.service;

import org.trash.smartbe.model.IOTDevice;

import java.util.List;

public interface IOTDeviceService {
    List<IOTDevice> getAllDevices();
    IOTDevice getDeviceById(Long deviceId); // Changed from String to Long
    IOTDevice createDevice(IOTDevice device);
    void deleteDevice(Long deviceId); // Changed from String to Long
    IOTDevice updateDevice(Long deviceId, IOTDevice updatedDevice); // Added update method
}
