package com.luiszamorano.softwaredocbuilder.entity.relation;

import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.Usuario_RolUniversidad_Universidad_PK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario_roluniversidad_universidad")
public class Usuario_RolUniversidad_Universidad {
    @EmbeddedId
    private Usuario_RolUniversidad_Universidad_PK usuarioRolUniversidadUniversidadPk;
}
