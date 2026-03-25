## Parking Lot LLD

### Step 1: Clarify Requirements

#### Functional Requirements

1) Enter Flow
- Vehicle arrives at Parking lot.
- Assign slot based on Vehicle type 
- Generate a ticket
- Mark slot as occupied
- Return entry response

2) Exit Flow
- Vehicle arrives at checkout station
- Enters ticket
- Calculate fee based on rules(flat/hourly)
- Process payment
- Release slot
- Return receipt and exit response

3) Admin Flow
- Add/delete/update floors and slots
- Define/update pricing rules
- View parking lot status


#### Non-Functional Requirements

1) Scalability: Multiple parking lots, multiple parking slots
2) Availability: The parking system should be working all the time i.e Entry/Exit should work even if payment fails
3) Consistency: Consistent parking slot status all the time
4) Extensibility: Easy addition of new vehicle types or gateways
5) Security: Role-based access for admin operations
6) Latency: < 500ms for key flows


#### Edge-Cases

1) What if no parking slots available for a specific vehicle type?
2) What if ticket is lost?
3) What if payment system fails?
4) What is ticket and vehicle don't match?
5) What if system clock is mismatched?
6) Slot marked occupied incorrectly?


### Step 2: Identify Core Entities

#### Entity 1: Vehicle 
- id : UUID
- VehicleType : Str
- LicensePlate: Str


#### Entity 2: Floor
- id : UUID
- floorNumber: int
- slots: int


#### Entity 3: Slot
- id: UUID
- slotType: Str
- floorNumber: int
- isOccupied: bool


#### Entity 4: Ticket
- id: UUID
- vehicleId: UUID
- slotId: UUID
- entryTimeUTC: DateTime
- isActive: bool


#### Entity 5: PricingRule
- vehicleType: Str
- ratePerHour: double
- flatRate: double
- ruleType: Str


#### Entity 6: Payment
- ticketId: UUID
- amount: double
- gatewayType: Str/Interface
- status

#### Entity 7: Receipt
- id: UUID
- ticketId: UUID
- exitTime: DateTime
- totalPayment: double
- paymentStatus: Str


#### Entity 8: EntryResult/ExitResult

- success: bool
- data: Str
- message: Str


### Step 3: Visual Interaction Flow

#### Flow 1: Entry Flow

```
Vehicle enters --> Slot allocated --> Ticket generated --> Slot marked as occupied
```


#### Flow 2: Exit Flow

```
Ticket scanned --> Fee calculated based on pricing rules --> Payment process (with retries) --> Receipt generated --> Slot released --> Ticket deactivated
```


#### Flow 3: Admin Flow

```
Add floor --> Add slot --> Update pricing 
```


### Step 4: Define Class Structures and Relationships

#### Layers of Architecture

Client Layer → Controller Layer → Service Layer → Repository Layer → Domain Layer


- The Client Layer is responsible for user interaction and presenting information to the user.
- The Controller Layer handles incoming requests from the client and delegates the tasks to the appropriate service.
- The Service Layer contains the business logic, such as ticket generation and fee calculation.
- The Repository Layer manages data access and persistence.
- The Domain Layer defines the core entities like vehicles, slots, tickets, etc.

#### Controllers:
- C1: EntryController (enterVehicle())
- C2: ExitController (exitVehicle())
- C3: AdminController (addFloor(), addSlot(), updatePricing())

#### Services:
- S1: TicketService
- S2: PaymentService
- S3: ReceiptService
- S4: SlotService
- S5: PricingService
- S6: AdminService

#### Repository:
- R1: TicketRepository
- R2: SlotRepository
- R3: FloorRepository
- R4: PricingRuleRepository
- R5: PaymentRepository


#### Interfaces and Adapters:

We will use interfaces and adapters to integrate external services: PaymentGatewayAdapter, RazorpayAdapter, StriperAdapter. The adapter pattern will allow for:

- Abstracting payment gateway interactions
- Easily switching or adding new payment services like Razorpay or Stripe by implementing the PaymentGatewayAdapter interface

### Step 5: Implement Core Use Cases

#### Use Case 1: Entry Use Case

```
enterVehicle() → SlotService.allocateSlot() → TicketService.generateTicket() → TicketRepository.save() → Return EntryResult
```


#### Use Case 2: Exit Use Case:

```
exitVehicle() → PricingService.ca() → TicketService.generateTicket() → TicketRepository.save() → Return EntryResult
```


#### Use Case 3:




exitVehicle() → Get Ticket → Calculate Fee → Process Payment (with retries) → Release Slot → Generate Receipt → Return ExitResult
Admin Use Cases:
addFloor() : Save new floor
addSlot() : Save new slot
updatePricing() : Update pricing rules

### Step 6: Apply OOP Principles & Design Patterns

#### Design Pattern Used:
- Adapter Pattern: Abstraction of payment gateways
- Repository Pattern: Isolation of database operations
- Service Layer Pattern: Centralization of business logic


#### OOP Principles Applied:

- SRP (Single Responsibility Principle): Each class has one clear responsibility
- ISP (Interface Segregation Principle): Role-specific interfaces (e.g., for payment)
- DIP (Dependency Inversion Principle): Services depend on interfaces, not concrete implementations
- Open/Closed Principle: The system is open for extension but closed for modification
- Encapsulation: Domain entities encapsulate both data and behavior


### Step 7: Handle Edge Case

- Edge Case 1 : Strategy
- Edge Case 2 : Strategy
- Edge Case 3 : Strategy


### Step 8: Package Structure

### Step 9: Class Diagram


