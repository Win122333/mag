package sel.catalogue.dto.mapper;

import org.api.http.dto.RequestProductDto;
import org.api.http.dto.ResponseProductDto;
import sel.catalogue.Entity.Product;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    Product mapToProduct(RequestProductDto dto);
    ResponseProductDto mapFromProduct(Product product);
}
