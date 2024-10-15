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
    public IOTDevice getDeviceById(String deviceId) {
        return iotDeviceDAO.findById(deviceId);
    }

    @Override
    public IOTDevice createDevice(IOTDevice device) {
        return iotDeviceDAO.save(device);
    }

    @Override
    public void deleteDevice(String deviceId) {
        iotDeviceDAO.deleteById(deviceId);
    }
}
