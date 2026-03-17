package joachim.lejeune.peditrack.model.user;

import java.awt.geom.Point2D;
import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private Point2D location;
    private List<String> roles;

    // Constructeurs
    public JwtResponse(String token, Long id, String username, Point2D location, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.location = location;
        this.roles = roles;
    }

    // Getters et Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Point2D getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
