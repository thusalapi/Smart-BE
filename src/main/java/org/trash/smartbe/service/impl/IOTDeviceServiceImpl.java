package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trash.smartbe.model.IOTDevice;
import org.trash.smartbe.repository.IOTDeviceDAO;
import org.trash.smartbe.service.IOTDeviceService;

import java.util.List;

@Service
public class IOTDeviceServiceImpl implements IOTDeviceService {

    private final IOTDeviceDAO iotDeviceDAO;

    @Autowired
    public IOTDeviceServiceImpl(IOTDeviceDAO iotDeviceDAO) {
        this.iotDeviceDAO = iotDeviceDAO;
    }

    @Override
    public List<IOTDevice> getAllDevices() {
        return iotDeviceDAO.findAll();
    }

    @Override
    public IOTDevice getDeviceById(Long deviceId) { // Changed from String to Long
        return iotDeviceDAO.findById(deviceId);
    }

    @Override
    public IOTDevice createDevice(IOTDevice device) {
        return iotDeviceDAO.save(device);
    }

    @Override
    public void deleteDevice(Long deviceId) { // Changed from String to Long
        iotDeviceDAO.deleteById(deviceId);
    }

    @Override
    public IOTDevice updateDevice(Long deviceId, IOTDevice updatedDevice) { // Added update method
        IOTDevice existingDevice = iotDeviceDAO.findById(deviceId);
        if (existingDevice != null) {
            // Update fields from updatedDevice to existingDevice
            existingDevice.setStatus(updatedDevice.isStatus());
            existingDevice.setReadWasteLevel(updatedDevice.getReadWasteLevel());
            existingDevice.setWasteBin(updatedDevice.getWasteBin()); // Optional, depending on your use case
            return iotDeviceDAO.save(existingDevice); // Save the updated device
        }
        return null; // or throw an exception if preferred
    }
}
