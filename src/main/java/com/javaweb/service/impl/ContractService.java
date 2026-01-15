package com.javaweb.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.ContractConverter;
import com.javaweb.entity.ContractEntity;
import com.javaweb.enums.ContractStatus;
import com.javaweb.model.dto.ContractDTO;
import com.javaweb.repository.ContractRepository;
import com.javaweb.service.IContractService;
import com.javaweb.service.PdfService;
import com.javaweb.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractService implements IContractService {

    private static final String PDF_DIR = "uploads/contracts/";

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractConverter contractConverter;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PdfService pdfService;

    @Override
    public void saveContract(ContractDTO contractDTO) {
        try {
            // 1️⃣ Tạo entity từ DTO
            ContractEntity entity = contractConverter.toEntity(contractDTO);

            // 2️⃣ Sinh file PDF
            Files.createDirectories(Paths.get(PDF_DIR));
            String html = Files.readString(Paths.get("src/main/resources/templates/contract_template.html"));

            html = html
                    // --- BÊN CHO THUÊ/BÁN (BÊN A) ---
                    .replace("${lessorName}", safe(contractDTO.getSellerName()))
                    .replace("${lessorIdNumber}", safe(contractDTO.getSellerIdNumber()))
                    .replace("${lessorIdIssueDate}", safe(contractDTO.getSellerIssueDate()))
                    .replace("${lessorIdIssuePlace}", safe(contractDTO.getSellerIssuePlace()))
                    .replace("${lessorAddress}", safe(contractDTO.getSellerAddress()))
                    .replace("${lessorPhone}", safe(contractDTO.getSellerPhone()))

                    // --- BÊN THUÊ/MUA (BÊN B) ---
                    .replace("${lesseeName}", safe(contractDTO.getBuyerName()))
                    .replace("${lesseeIdNumber}", safe(contractDTO.getBuyerIdNumber()))
                    .replace("${lesseeIdIssueDate}", safe(contractDTO.getBuyerIssueDate()))
                    .replace("${lesseeIdIssuePlace}", safe(contractDTO.getBuyerIssuePlace()))
                    .replace("${lesseeAddress}", safe(contractDTO.getBuyerAddress()))
                    .replace("${lesseePhone}", safe(contractDTO.getBuyerPhone()))

                    // --- THÔNG TIN BẤT ĐỘNG SẢN ---
                    .replace("${propertyType}", safe(contractDTO.getPropertyType()))
                    .replace("${propertyAddress}", safe(contractDTO.getPropertyAddress()))
                    .replace("${propertyArea}", safe(contractDTO.getPropertyArea()))
                    .replace("${propertyRooms}", safe(contractDTO.getPropertyRooms()))
                    .replace("${propertyCertificate}", safe(contractDTO.getPropertyCertificateNumber()))
                    .replace("${rentPrice}", safe(contractDTO.getRentalPrice()))
                    .replace("${salePrice}", safe(contractDTO.getTotalPrice()))
                    .replace("${contractDuration}", safe(contractDTO.getRentalDuration()))

                    // --- THÔNG TIN HỢP ĐỒNG ---
                    .replace("${contractCode}", safe(contractDTO.getContractCode()))
                    .replace("${contractDate}", safe(contractDTO.getContractDate()))
                    .replace("${location}", safe(contractDTO.getPropertyCertificatePlace())) // hoặc thêm field riêng location
                    .replace("${description}", safe(contractDTO.getDescription()))

                    // --- ĐIỀU KHOẢN (nếu chưa có dữ liệu thực tế thì để trống) ---
                    .replace("${clause1}", "")
                    .replace("${clause2}", "")
                    .replace("${clause3}", "")
                    .replace("${clause4}", "")
                    .replace("${clause5}", "")
                    .replace("${clause6}", "")
                    .replace("${clause7}", "")

                    // --- NGÀY HIỆU LỰC (nếu có thời gian thuê cụ thể) ---
                    .replace("${startDate}", safe(contractDTO.getStartDate()))
                    .replace("${endDate}", safe(contractDTO.getEndDate()));





            String pdfFile = PDF_DIR + "hopdong_" + contractDTO.getContractCode() + ".pdf";

            try (OutputStream out = new FileOutputStream(pdfFile)) {
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, out);
                document.open();
                XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(html));
                document.close();
            }

            // 3️⃣ Lưu thông tin file + metadata
            entity.setContractDate(LocalDate.now());
            entity.setPdfPath(pdfFile);
            entity.setCreatedBy("admin");
            entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            entity.setStatus(ContractStatus.DRAFT);

            // 4️⃣ Lưu DB
            contractRepository.save(entity);

            System.out.println("✅ Tạo hợp đồng thành công và đã lưu file PDF tại: " + pdfFile);

        } catch (Exception e) {
            throw new RuntimeException("❌ Lỗi khi tạo hợp đồng: " + e.getMessage(), e);
        }
    }

//    @Override
//    public ContractDTO getContractById(Long customerId) {
//        ContractEntity contractEntity = contractRepository.findByCustomerId(customerId);
//        if (contractEntity == null) {
//            return null;
//        }
//        return contractConverter.toDTO(contractEntity);
//    }

    @Override
    public List<ContractDTO> getContractsByCustomerId(Long customerId) {
        List<ContractEntity> entities = contractRepository.findAllByCustomerId(customerId);
        return entities.stream().map(contractConverter::toDTO).toList();
    }


    @Override
    public byte[] getContractPdfById(Long id) {
        String pdfPath = contractRepository.getContractPdfPathById(id);
        if (pdfPath == null) {
            throw new RuntimeException("Không tìm thấy đường dẫn file PDF cho hợp đồng ID " + id);
        }

        try {
            Path path = Paths.get(pdfPath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi đọc file PDF: " + e.getMessage(), e);
        }
    }

    @Override
    public void putAll(ContractDTO contractDTO) {
        ContractEntity contractEntity = contractRepository.findById(contractDTO.getId()).orElse(null);
        if (contractEntity != null) {
            modelMapper.map(contractDTO, contractEntity);

            // Nếu file PDF mới được upload lên từ frontend
            if (contractDTO.getPdfPath() != null) {
                Path pdfPath = Paths.get("uploads/contracts/hopdong_" + contractEntity.getContractCode() + ".pdf");
                Path newPdfPath = Path.of(PDF_DIR + "hopdong_" + contractDTO.getContractCode() + ".pdf");
                try {
                    // Xoá file cũ
                    if (Files.exists(pdfPath)) {
                        Files.delete(pdfPath);
                    }

                    // Copy file PDF mới vào đúng vị trí
                    Files.copy(Paths.get(contractDTO.getPdfPath()), pdfPath);

                    // Cập nhật lại đường dẫn
                    contractEntity.setPdfPath(newPdfPath.toString());
                } catch (IOException e) {
                    throw new RuntimeException("Không thể cập nhật file PDF hợp đồng", e);
                }
            }

            contractRepository.save(contractEntity);
        }
    }




    @Override
    public List<ContractDTO> getAllContracts() {
        ContractDTO contractEntity = new ContractDTO();
        Long currentUserId = SecurityUtils.getCurrentUserId();
        boolean isAdmin = SecurityUtils.isAdmin();
        boolean isUsers = SecurityUtils.isUser();


        List<ContractEntity> entities;

        if (currentUserId == null) {
            entities = contractRepository.findAll();
        }

        else if (isAdmin) {
            entities = contractRepository.findAll();
        }

        else if (isUsers){
            entities = contractRepository.findAll();
        }

        else {
            entities = contractRepository.findByStaffId(currentUserId,contractEntity);
        }

        return entities.stream().map(entity -> {
            ContractDTO dto = new ContractDTO();
            dto.setId(entity.getId());
            dto.setContractCode(entity.getContractCode());
            dto.setBuyerName(entity.getBuyerName());
            dto.setBuyerPhone(entity.getBuyerPhone());
            dto.setPropertyType(entity.getPropertyType());
            dto.setTotalPrice(entity.getTotalPrice());
            dto.setContractDate(entity.getContractDate());
            dto.setStatus(entity.getStatus().name());
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public void deleteAll(Long[] ids) {
        try {
            for (Long id : ids) {
                contractRepository.findById(id).ifPresent(contractRepository::delete);
            }
        } catch (Exception e) {
            throw new RuntimeException("Xoá lỗi rồi", e);
        }
    }

    @Transactional
    public void updateStatus(Long id, String status) {
        ContractEntity contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        contract.setStatus(ContractStatus.valueOf(String.valueOf(ContractStatus.valueOf(status))));
        contractRepository.save(contract);
    }



    private String safe(Object value) {
        return value == null ? "" : value.toString();
    }

}
