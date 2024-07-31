package net.javaguides.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.entity.Department;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.DepartmentMapper;
import net.javaguides.ems.repository.DepartmentRepository;
import net.javaguides.ems.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepository departmentRepository;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	@Override
	public DepartmentDto createDepartment(DepartmentDto departmentDto) {
		Department department = DepartmentMapper.mapToDepartment(departmentDto);
		Department savedDepartment = departmentRepository.save(department);
		DepartmentDto savedDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);
		return savedDepartmentDto;
	}

	@Override
	public DepartmentDto getDepartment(Long departmentId) {
		Department department = departmentRepository.findById(departmentId).orElseThrow(() -> 
			new ResourceNotFoundException("Department does not exists with given id : "+ departmentId));
		DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);
		return departmentDto;
	}

	@Override
	public List<DepartmentDto> getAllDepartments() {
		List<Department> departments = departmentRepository.findAll();
		return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department)).collect(Collectors.toList());
	}

	@Override
	public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department is not exits with given id : "+ departmentId)
		);
		
		department.setDepartmentName(updatedDepartment.getDepartmentName());
		department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());
		
		Department savedDepartment = departmentRepository.save(department);
		return DepartmentMapper.mapToDepartmentDto(savedDepartment);
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department is not exists with given id : "+ departmentId)
		);
		departmentRepository.deleteById(departmentId);
	}

}
