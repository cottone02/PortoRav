package it.rjcsoft.prv.dto.modulistica;

import org.springframework.web.multipart.MultipartFile;

public class ModulisticaUpdateDTO {

    //@NotNull(message = "Id must be not null")
    private Integer id;

    private String name;

    private String description;

    private MultipartFile file;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ModulisticaUpdateDTO [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", description=");
        builder.append(description);
        builder.append(", file=");
        builder.append(file);
        builder.append("]");
        return builder.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
