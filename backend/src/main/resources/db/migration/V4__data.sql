-- Root sectors (no parent)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'Manufacturing', NOW(), NOW(), NULL),
                                                                      ('6ba7b810-9dad-11d1-80b4-00c04fd430c8', 'Other', NOW(), NOW(), NULL),
                                                                      ('6ba7b811-9dad-11d1-80b4-00c04fd430c8', 'Service', NOW(), NOW(), NULL);

-- Manufacturing sub-sectors (Level 1)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Construction materials', NOW(), NOW(), 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
                                                                      ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Electronics and Optics', NOW(), NOW(), 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
                                                                      ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Food and Beverage', NOW(), NOW(), 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
                                                                      ('d0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Furniture', NOW(), NOW(), 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
                                                                      ('e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Machinery', NOW(), NOW(), 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
                                                                      ('f0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Metalworking', NOW(), NOW(), 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
                                                                      ('10eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Plastic and Rubber', NOW(), NOW(), 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
                                                                      ('20eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Printing', NOW(), NOW(), 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
                                                                      ('30eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Textile and Clothing', NOW(), NOW(), 'f47ac10b-58cc-4372-a567-0e02b2c3d479'),
                                                                      ('40eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Wood', NOW(), NOW(), 'f47ac10b-58cc-4372-a567-0e02b2c3d479');

-- Food and Beverage sub-sectors (Level 2)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('c1eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Bakery & confectionery products', NOW(), NOW(), 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Beverages', NOW(), NOW(), 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('c3eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Fish & fish products', NOW(), NOW(), 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('c4eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Meat & meat products', NOW(), NOW(), 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('c5eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Milk & dairy products', NOW(), NOW(), 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('c6eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Other', NOW(), NOW(), 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('c7eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Sweets & snack food', NOW(), NOW(), 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Furniture sub-sectors (Level 2)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('d1eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Bathroom/sauna', NOW(), NOW(), 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('d2eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Bedroom', NOW(), NOW(), 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('d3eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Children''s room', NOW(), NOW(), 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('d4eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Kitchen', NOW(), NOW(), 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('d5eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Living room', NOW(), NOW(), 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('d6eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Office', NOW(), NOW(), 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('d7eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Other (Furniture)', NOW(), NOW(), 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('d8eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Outdoor', NOW(), NOW(), 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('d9eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Project furniture', NOW(), NOW(), 'd0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Machinery sub-sectors (Level 2)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('e1eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Machinery components', NOW(), NOW(), 'e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('e2eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Machinery equipment/tools', NOW(), NOW(), 'e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('e3eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Manufacture of machinery', NOW(), NOW(), 'e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Maritime', NOW(), NOW(), 'e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('e5eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Metal structures', NOW(), NOW(), 'e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('e6eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Other', NOW(), NOW(), 'e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('e7eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Repair and maintenance service', NOW(), NOW(), 'e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Maritime sub-sectors (Level 3)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('e7e1bc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Aluminium and steel workboats', NOW(), NOW(), 'e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('e7e2bc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Boat/Yacht building', NOW(), NOW(), 'e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('e7e3bc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Ship repair and conversion', NOW(), NOW(), 'e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Metalworking sub-sectors (Level 2)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('f1eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Construction of metal structures', NOW(), NOW(), 'f0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('f2eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Houses and buildings', NOW(), NOW(), 'f0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('f3eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Metal products', NOW(), NOW(), 'f0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('f4eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Metal works', NOW(), NOW(), 'f0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Metal works sub-sectors (Level 3)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('f41ebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'CNC-machining', NOW(), NOW(), 'f4eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('f42ebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Forgings, Fasteners', NOW(), NOW(), 'f4eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('f43ebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Gas, Plasma, Laser cutting', NOW(), NOW(), 'f4eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('f44ebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'MIG, TIG, Aluminum welding', NOW(), NOW(), 'f4eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Plastic and Rubber sub-sectors (Level 2)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('11eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Packaging', NOW(), NOW(), '10eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('12eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Plastic goods', NOW(), NOW(), '10eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('13eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Plastic processing technology', NOW(), NOW(), '10eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('14eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Plastic profiles', NOW(), NOW(), '10eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Plastic processing technology sub-sectors (Level 3)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('131ebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Blowing', NOW(), NOW(), '13eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('132ebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Moulding', NOW(), NOW(), '13eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('133ebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Plastics welding and processing', NOW(), NOW(), '13eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Printing sub-sectors (Level 2)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('21eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Advertising', NOW(), NOW(), '20eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('22eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Book/Periodicals printing', NOW(), NOW(), '20eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('23eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Labelling and packaging printing', NOW(), NOW(), '20eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Textile and Clothing sub-sectors (Level 2)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('31eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Clothing', NOW(), NOW(), '30eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('32eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Textile', NOW(), NOW(), '30eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Wood sub-sectors (Level 2)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('41eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Other (Wood)', NOW(), NOW(), '40eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('42eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Wooden building materials', NOW(), NOW(), '40eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
                                                                      ('43eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Wooden houses', NOW(), NOW(), '40eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Other main category sub-sectors (Level 1)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('6ba7b812-9dad-11d1-80b4-00c04fd430c8', 'Creative industries', NOW(), NOW(), '6ba7b810-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b813-9dad-11d1-80b4-00c04fd430c8', 'Energy technology', NOW(), NOW(), '6ba7b810-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b814-9dad-11d1-80b4-00c04fd430c8', 'Environment', NOW(), NOW(), '6ba7b810-9dad-11d1-80b4-00c04fd430c8');

-- Service sub-sectors (Level 1)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('6ba7b815-9dad-11d1-80b4-00c04fd430c8', 'Business services', NOW(), NOW(), '6ba7b811-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b816-9dad-11d1-80b4-00c04fd430c8', 'Engineering', NOW(), NOW(), '6ba7b811-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b817-9dad-11d1-80b4-00c04fd430c8', 'Information Technology and Telecommunications', NOW(), NOW(), '6ba7b811-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b818-9dad-11d1-80b4-00c04fd430c8', 'Tourism', NOW(), NOW(), '6ba7b811-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b819-9dad-11d1-80b4-00c04fd430c8', 'Translation services', NOW(), NOW(), '6ba7b811-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b81a-9dad-11d1-80b4-00c04fd430c8', 'Transport and Logistics', NOW(), NOW(), '6ba7b811-9dad-11d1-80b4-00c04fd430c8');

-- Information Technology and Telecommunications sub-sectors (Level 2)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('6ba7b81b-9dad-11d1-80b4-00c04fd430c8', 'Data processing, Web portals, E-marketing', NOW(), NOW(), '6ba7b817-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b81c-9dad-11d1-80b4-00c04fd430c8', 'Programming, Consultancy', NOW(), NOW(), '6ba7b817-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b81d-9dad-11d1-80b4-00c04fd430c8', 'Software, Hardware', NOW(), NOW(), '6ba7b817-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b81e-9dad-11d1-80b4-00c04fd430c8', 'Telecommunications', NOW(), NOW(), '6ba7b817-9dad-11d1-80b4-00c04fd430c8');

-- Transport and Logistics sub-sectors (Level 2)
INSERT INTO sector (id, name, created_at, modified_at, parent_id) VALUES
                                                                      ('6ba7b81f-9dad-11d1-80b4-00c04fd430c8', 'Air', NOW(), NOW(), '6ba7b81a-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b820-9dad-11d1-80b4-00c04fd430c8', 'Rail', NOW(), NOW(), '6ba7b81a-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b821-9dad-11d1-80b4-00c04fd430c8', 'Road', NOW(), NOW(), '6ba7b81a-9dad-11d1-80b4-00c04fd430c8'),
                                                                      ('6ba7b822-9dad-11d1-80b4-00c04fd430c8', 'Water', NOW(), NOW(), '6ba7b81a-9dad-11d1-80b4-00c04fd430c8');
