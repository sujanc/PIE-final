package com.stackroute.pie.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

    @Document(collection = "roles")
    public class Role {
        @Id
        private Long id;




        private RoleName name;

        public Role() {}

        public Role(RoleName name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public RoleName getName() {
            return name;
        }

        public void setName(RoleName name) {
            this.name = name;
        }
    }


