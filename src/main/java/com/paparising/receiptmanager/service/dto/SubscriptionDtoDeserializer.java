
package com.paparising.receiptmanager.service.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.paparising.receiptmanager.domain.Creator;
import com.paparising.receiptmanager.domain.Marketplace;

import java.io.IOException;

public class SubscriptionDtoDeserializer extends JsonDeserializer<SubscriptionDTO> {
    @Override
    public SubscriptionDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode subscriptionNode = jsonParser.getCodec().readTree(jsonParser);
        String type = subscriptionNode.path("type").asText();
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setType(type);
        dto.setMarketplace(extractMarketplace(subscriptionNode));
        dto.setCreator(extractCreator(subscriptionNode));
        // to do add the payload object
        return dto;
    }
    
    protected Marketplace extractMarketplace(JsonNode rootNode) {
        String baseUrl = rootNode.path("marketplace").path("baseUrl").asText();
        String partner = rootNode.path("marketplace").path("partner").asText();
        Marketplace marketplace = new Marketplace();
        marketplace.setBaseUrl(baseUrl);
        marketplace.setPartner(partner);
        
        return marketplace;
    }
    
    protected Creator extractCreator(JsonNode rootNode) {
        String address = rootNode.path("creator").path("address").path("fullName").asText();
        String email = rootNode.path("creator").path("email").asText();
        String firstName = rootNode.path("creator").path("firstName").asText();
        String language = rootNode.path("creator").path("language").asText();
        String lastName = rootNode.path("creator").path("lastName").asText();
        String locale = rootNode.path("creator").path("locale").asText();
        String openId = rootNode.path("creator").path("openId").asText();
        String uuid = rootNode.path("creator").path("uuid").asText();

        return new Creator(address, firstName, lastName, email, language, locale, openId, uuid);
    }


}
