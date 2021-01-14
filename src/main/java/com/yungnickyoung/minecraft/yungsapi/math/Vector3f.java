package com.yungnickyoung.minecraft.yungsapi.math;

/**
 * Simple implementation of Vector2f to avoid requiring external dependencies, esp. for FastNoise.
 */
public class Vector3f {
    public float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(float[] v) {
        this(v[0], v[1], v[2]);
    }

    public Vector3f(Vector3f v1) {
        this(v1.x, v1.y, v1.z);
    }

    public final float dot(Vector3f v1) {
        return this.x * v1.x + this.y * v1.y + this.z * v1.z;
    }

    public final float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public final float lengthSquared() {
        return (x * x + y * y + z * z);
    }
}
