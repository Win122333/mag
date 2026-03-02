package sel.catalogue.dto.mapper;

import sel.catalogue.Entity.Product;
import sel.catalogue.dto.RequestProductDto;
import sel.catalogue.dto.ResponseProductDto;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    Product mapToProduct(RequestProductDto dto);
    ResponseProductDto mapFromProduct(Product product);
}
