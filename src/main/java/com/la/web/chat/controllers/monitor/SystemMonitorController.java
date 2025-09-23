package com.la.web.chat.controllers.monitor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor")
public class SystemMonitorController {

	@GetMapping("/memory")
	public Map<String, Object> getMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();

		long totalMemory = runtime.totalMemory(); // Memoria total asignada al JVM
		long freeMemory = runtime.freeMemory(); // Memoria libre dentro de la JVM
		long usedMemory = totalMemory - freeMemory; // Memoria usada
		long maxMemory = runtime.maxMemory(); // Memoria máxima disponible al JVM

		Map<String, Object> memoryInfo = new HashMap<>();
		memoryInfo.put("usedMemoryMB", usedMemory / (1024 * 1024));
		memoryInfo.put("freeMemoryMB", freeMemory / (1024 * 1024));
		memoryInfo.put("totalMemoryMB", totalMemory / (1024 * 1024));
		memoryInfo.put("maxMemoryMB", maxMemory / (1024 * 1024));

		return memoryInfo;
	}
}
