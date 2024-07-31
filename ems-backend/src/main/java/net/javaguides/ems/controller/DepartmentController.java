package net.javaguides.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	private DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}
	
	// Build create or add REST API
	@PostMapping
	public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
		DepartmentDto createdDepartmentDto = departmentService.createDepartment(departmentDto);
		return new ResponseEntity<>(createdDepartmentDto, HttpStatus.CREATED);
	}
	
	// Build get by id REST API
	@GetMapping("{id}")
	public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("id") Long departmentId){
		DepartmentDto departmentDto = departmentService.getDepartment(departmentId);
		return ResponseEntity.ok(departmentDto);
	}
	
	// Build get all department REST API
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
		List<DepartmentDto> departments = departmentService.getAllDepartments();
		return ResponseEntity.ok(departments);
	}
	
	// Build update department REST API
	@PutMapping("{id}")
	public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId, @RequestBody DepartmentDto updatedDepartment){
		DepartmentDto updatedDepartmentDto = departmentService.updateDepartment(departmentId, updatedDepartment);
		return ResponseEntity.ok(updatedDepartmentDto);
	}
	
	// Build delete department REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
		departmentService.deleteDepartment(departmentId);
		return ResponseEntity.ok("Department deleted successfully!!!");
	}
}
